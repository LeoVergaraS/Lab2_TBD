<template>
    <v-container class="">
      <v-row no-gutters>
        <v-col order="1">
          <v-card
            class="pa-2"
            outlined
            tile
          >
          Emergencias
          <v-data-table :headers="headers" :items="tareas" class="elevation-1"></v-data-table>
          </v-card>
        </v-col>
        <v-col order="2">
          <v-card
            class="pa-2"
            outlined
            tile
          >
          Tareas
          <v-data-table :headers="headers" :items="tareas" class="elevation-1"></v-data-table>
          </v-card>
        </v-col>
        <v-col order="3">
          <v-card
            class="pa-2"
            outlined
            tile
          >
          Voluntarios
          <v-data-table :headers="headers" :items="tareas" class="elevation-1"></v-data-table>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </template>

<script>
import axios from "axios"
export default{
    data: () =>({
        headersE:{},
        headersT:{},
        headersV:{}
    })

}
</script>