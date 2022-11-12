<template>
    <v-container class="">
        <v-row no-gutters>
            <v-col order="1">
                <v-card class="pa-2" outlined tile>
                    Emergencias
                    <v-data-table :headers="headersE" :items="emergencias" class="elevation-1">
                        <template v-slot:[`item.action`]="{ item }">
                            <v-icon small class="mr-2" @click="getTasks(item.id)"> mdi-arrow-right </v-icon>
                        </template>
                        <template v-slot:[`item.estado`]="{ item }">
                            <div v-text="getNombre(item.estado)"></div>
                        </template>
                    </v-data-table>
                </v-card>
            </v-col>
            <v-col order="2">
                <v-card class="pa-2" outlined tile>
                    Tareas
                    <v-data-table :headers="headersT" :items="tareas" class="elevation-1">
                        <template v-slot:[`item.action`]="{ item }">
                            <v-icon small class="mr-2" @click="getVolunteers(item.id)"> mdi-arrow-right </v-icon>
                        </template>
                        <template v-slot:[`item.id_estado`]="{ item }">
                            <div v-text="getNombre(item.id_estado)"></div>
                        </template>
                    </v-data-table>
                </v-card>
            </v-col>
            <v-col order="3">
                <v-card class="pa-2" outlined tile>
                    Voluntarios
                    <v-data-table :headers="headersV" :items="voluntarios" class="elevation-1">
                        <template v-slot:[`item.flg_participa`]="{ item }">
                            <div v-text="getSiNo(item.flg_participa)"></div>
                        </template>
                    </v-data-table>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from "axios"
export default {
    data: () => ({
        headersE: [
            {
                text: "Id",
                align: "start",
                sortable: false,
                value: "id",
            },
            { text: "Nombre", value: "nombre" },
            { text: "Estado", value: "estado" },
            { text: "Elegir", value: "action" }
        ],
        headersT: [
            {
                text: "Id",
                align: "start",
                sortable: false,
                value: "id",
            },
            { text: "Nombre", value: "nombre" },
            { text: "Requerido", value: "cant_vol_requeridos" },
            { text: "Estado", value: "id_estado" },
            { text: "Elegir", value: "action" }
        ],
        headersV: [
            {
                text: "Rut",
                align: "start",
                sortable: false,
                value: "rut",
            },
            { text: "Nombre", value: "nombre" },
            { text: "Participa", value: "flg_participa" },
        ],
        emergencias: [],
        tareas: [],
        voluntarios: [],
        estadosTareas: []
    }),

    async created() {
        await this.getStatusTask()
        await this.getEmergencies()
    },

    methods: {
        async getEmergencies() {
            const url = "http://localhost:8090/emergencies"
            await axios.get(url)
                .then((response) => {
                    this.emergencias = response.data
                })
                .catch((error) => {
                    console.log(error)
                })
        },
        async getTasks(id_e) {
            const url = "http://localhost:8090/tasks/emergency/" + String(id_e)
            await axios
                .get(url)
                .then((response) => {
                    this.tareas = response.data
                })
                .catch((error) => {
                    console.log(error)
                })
        },
        async getVolunteers(id_t) {
            const url = "http://localhost:8090/volunteers/task/" + String(id_t)
            await axios.get(url)
                .then((response) => {
                    this.voluntarios = response.data
                })
                .catch((error) => {
                    console.log(error)
                })
        },
        async getStatusTask() {
            const url = "http://localhost:8090/status_tasks";
            await axios
                .get(url)
                .then((response) => {
                    this.estadosTareas = response.data.sort((a, b) => a.id - b.id);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getNombre(id) {
            return this.estadosTareas[id].descrip;
        },
        getSiNo(a) {
            if(a == 1){
                return "Si"
            }else{
                return "No"
            }
        }
    }

}
</script> 