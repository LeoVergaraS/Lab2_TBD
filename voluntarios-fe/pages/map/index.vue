<template>
    <div class="map">
      <h1>Ejemplo Mapas</h1>
      <v-select
        v-model="id_emergencia"
        :items="emergencias"
        item-text="nombre"
        item-value="id"
        label="Emergencia"
        required
      >
      </v-select>
      <div>{{point}} 
        <input type="text" v-model="name" placeholder="nombre" />
        <button type="button" @click="createPoint">Crear</button>
      </div>
      <div>{{message}}</div>
      <div id="mapid"></div>
    </div>
  </template>
  <script>
  //Importaciones
  import 'leaflet/dist/leaflet'; //librería leaflet
  import 'leaflet/dist/leaflet.css'; //css leaflet
  var icon = require('leaflet/dist/images/marker-icon.png'); //ícono de marcadores
  //Se crea objeto ícono con el marcador
  var LeafIcon = L.Icon.extend({
            options: {iconSize:[25, 41], iconAnchor:[12, 41], popupAnchor: [-3, -41]}
        });
  var myIcon = new LeafIcon({iconUrl: icon});
  
  //librería axios
  import axios from 'axios';
  export default {
    name: 'map',
    data:function(){
      return{
        latitude:null, //Datos de nuevo punto
        longitude:null,
        name:'',
        points:[], //colección de puntos cargados de la BD
        message:'', 
        mymap:null, //objeto de mapa(DIV)
        emergencias: [],
        id_emergencia: -1
      }
    },
    computed:{
      point(){ // función computada para representar el punto seleccionado
        if(this.latitude && this.longitude){
          let lat = this.latitude.toFixed(3);
          let lon = this.longitude.toFixed(3);
          return `(${lat}, ${lon})`;
        }else{
          return '';
        }
      }
    },
    methods:{
      async getEmergencies(){
        const url = "http://localhost:8090/emergencies";
        await axios.get(url)
          .then((response) => {
            this.emergencias = response.data.sort((a, b) => a.id - b.id)
          })
          .catch((error) => {
            console.log(error)
          })
      },
      clearMarkers:function(){ //eliminar marcadores
      
        this.points.forEach(p=>{
          this.mymap.removeLayer(p);
        })
        this.points = [];
      },
      async getPoints(map){
        try {
          //se llama el servicio 
          let response = await axios.get('http://localhost:8090/volunteers');
          let dataPoints = response.data;
          //Se itera por los puntos
          dataPoints.forEach(point => {
  
            //Se crea un marcador por cada punto
            let p =[point.latitude, point.longitude]
            let marker = L.marker(p, {icon:myIcon}) //se define el ícono del marcador
            .bindPopup(point.name) //Se agrega un popup con el nombre
            
            //Se agrega a la lista
            this.points.push(marker);
          });
  
          //Los puntos de la lista se agregan al mapa
          this.points.forEach(p=>{
            p.addTo(map)
          })
        } catch (error) {
         console.log('error', error); 
        }
        
      }
    },
    mounted:function(){
      this.getEmergencies()
      let _this = this;
      //Se asigna el mapa al elemento con id="mapid"
       this.mymap = L.map('mapid').setView([-33.456, -70.648], 7);
      //Se definen los mapas de bits de OSM
      L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
          attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
          maxZoom: 10
      }).addTo(this.mymap);
  
      //Evento click obtiene lat y long actual
      this.mymap.on('click', function(e) {
        _this.latitude = e.latlng.lat;
        _this.longitude =e.latlng.lng;
      });
  
      //Se agregan los puntos mediante llamada al servicio
      this.getPoints(this.mymap);
    }
  }
  </script>
  <style>
  .map{
    display:flex;
    flex-direction: column;
    align-items: center;
  }
  /* Estilos necesarios para definir el objeto de mapa */
  #mapid { 
    height: 400px; 
    width:600px;
  }
  </style>