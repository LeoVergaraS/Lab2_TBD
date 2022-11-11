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
      <v-btn color="blue darken-1" text @click="mostrarPuntos">
        Mostrar Ubicaciones
      </v-btn>

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
        latitud:null, //Datos de nuevo punto
        longitud:null,
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
        if(this.latitud && this.longitud){
          let lat = this.latitud.toFixed(3);
          let lon = this.longitud.toFixed(3);
          return `(${lat}, ${lon})`;
        }else{
          return '';
        }
      }
    },
    methods:{
      mostrarPuntos(){
        if(this.id_emergencia == -1){
          console.log('Debe seleccionar una emergencia')
        }else{
          this.clearMarkers()
          //Se agregan los puntos mediante llamada al servicio
          this.getPoints(this.mymap);
        }
      },
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
          const url = 'http://localhost:8090/volunteers/emergency/' + String(this.id_emergencia);
          let response = await axios.get( url );
          console.log("id emergencia: " + this.id_emergencia)
          console.log(response.data)
          let dataPoints = response.data;
          //Se itera por los puntos
          dataPoints.forEach(point => {
            let info = point.nombre + ", " + point.fnacimiento + ", " + point.longitud + ", " + point.latitud
            //Se crea un marcador por cada punto
            let p =[point.latitud, point.longitud]
            let marker = L.marker(p, {icon:myIcon}) //se define el ícono del marcador
            .bindPopup(info); //Se agrega un popup con el nombre

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
      /* this.mymap.on('click', function(e) {
        _this.latitud = e.latlng.lat;
        _this.longitud =e.latlng.lng;
      }); */
    }
  }
  </script>
  <style>
  .map{
    display:flex;
    flex-direction: column;
    align-items: center;
    position:relative;
    z-index: 0;
  }
  /* Estilos necesarios para definir el objeto de mapa */
  #mapid { 
    height: 400px; 
    width:600px;
  }
  </style>