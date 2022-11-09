<template>
  <!--tabla con el listado -->
  <v-data-table :headers="headers" :items="tareas" class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat>
        <!-- Título del listado y botones globales -->
        <v-toolbar-title>Tareas</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">
              Crear tarea
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">{{ formTitle }}</span>
            </v-card-title>

            <!--Atributos del elemento editado o creado -->
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <!-- nombre -->
                    <v-text-field
                      v-model="editedItem.nombre"
                      label="Nombre"
                      type="text"
                      :rules="[rules.required, rules.counter]"
                      counter
                      maxlength="60"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <!-- descripción -->
                    <v-text-field
                      v-model="editedItem.descrip"
                      label="Descripción"
                      type="text"
                      :rules="[rules.required]"
                      counter
                      maxlength="300"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <!-- cantidad de voluntarios -->
                    <v-text-field
                      v-model="editedItem.cant_vol_requeridos"
                      label="Voluntarios requeridos"
                      type="number"
                      :rules="[rules.required, rules.min]"
                      min="0"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <!-- Voluntarios inscritos -->
                    <v-text-field
                      v-model="editedItem.cant_vol_inscritos"
                      label="Voluntarios inscritos"
                      type="number"
                      :rules="[rules.required, rules.min]"
                      :max="max"
                      min="0"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <!-- fecha de inicio -->
                    <v-text-field
                      v-model="editedItem.finicio"
                      label="Fecha de inicio"
                      type="date"
                      :rules="[rules.required]"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <!-- fecha de fin -->
                    <v-text-field
                      v-model="editedItem.ffin"
                      label="Fecha de fin"
                      type="date"
                      :rules="[rules.required]"
                      required
                    ></v-text-field>
                  </v-col>

                  <!-- Nombre del id del estado, se hace mediante un select-->
                  <v-col cols="12" sm="6" md="4">
                    <v-select
                      v-model="editedItem.id_estado"
                      :items="estados"
                      item-text="descrip"
                      item-value="id"
                      label="Estado"
                      required
                    >
                    </v-select>
                  </v-col>

                  <!-- Nombre del id de la emergencia, se hace mediante un select -->
                  <v-col cols="12" sm="6" md="4">
                    <v-select
                      v-model="editedItem.id_emergencia"
                      :items="emergencias"
                      item-text="nombre"
                      item-value="id"
                      label="Emergencia"
                      required
                    >
                    </v-select>
                  </v-col>

                  <v-col cols="12" sm="6">
                    <v-select
                      v-model="nuevosRequerimientos"
                      :items="requerimientos"
                      item-text="descrip"
                      attach
                      requerimientos
                      label="Requerimientos"
                      multiple
                      return-object
                    ></v-select>
                  </v-col>

                </v-row>
              </v-container>
            </v-card-text>

            <!-- Botones de guardar y cancelar -->
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">
                Cancelar
              </v-btn>
              <v-btn color="blue darken-1" text href="#" @click="save">
                Guardar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <!-- Mensaje de diálogo de eliminar -->
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="text-h5"
              >¿Está seguro que desea eliminar la tarea?</v-card-title
            >
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete"
                >Cancelar</v-btn
              >
              <v-btn color="blue darken-1" text @click="deleteItemConfirm"
                >OK</v-btn
              >
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>

    <!-- Botón de editar y de eliminar -->
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
      <v-btn icon href="/task">
        <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
      </v-btn>
    </template>

    <!-- nombres de id_estado y id_emergencia -->
    <template v-slot:[`item.id_estado`]="{ item }">
      <div v-text="getNombre(item.id_estado, 0)"></div>
    </template>
    <template v-slot:[`item.id_emergencia`]="{ item }">
      <div v-text="getNombre(item.id_emergencia, 1)"></div>
    </template>
  </v-data-table>
</template>

<script>
/* Conexión con axios */
import axios from "axios";

/* Data ocupada para tener el listado, el edit y también el crear */
export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    rules: {
      required: (value) => !!value || "Requerido.",
      counter: (value) => value.length <= 60 || "Max de 60 caracteres",
      min: (value) => value > 0 || "Solo valores positivos",
    },
    /* Los header obtienen la información de los atributos y se le agrega un label */
    headers: [
      {
        text: "Id",
        align: "start",
        sortable: false,
        value: "id",
      },
      { text: "Nombre", value: "nombre" },
      { text: "Descripción", value: "descrip" },
      { text: "Voluntarios requeridos", value: "cant_vol_requeridos" },
      { text: "Voluntarios inscritos", value: "cant_vol_inscritos" },
      { text: "Fecha de inicio", value: "finicio" },
      { text: "Fecha de fin", value: "ffin" },
      { text: "Estado", value: "id_estado" },
      { text: "Emergencia", value: "id_emergencia" },
      { text: "Acciones", value: "actions" },
    ],

    /* Se inicializan variables a usar */
    tareas: [],
    estados: [],
    emergencias: [],
    requerimientos: [],
    nuevosRequerimientos: [],
    max: 0,
    editedIndex: -1,
    editedItem: {
      id: 0,
      nombre: "",
      descrip: "",
      cant_vol_requeridos: 0,
      cant_vol_inscritos: 0,
      finicio: "",
      ffin: "",
      id_estado: 0,
      id_emergencia: 0,
    },
    task_skill: {
      id: null,
      id_emehab: 0,
      id_tarea: 0,
    },
    defaultItem: {
      id: 0,
      nombre: "",
      descrip: "",
      cant_vol_requeridos: 0,
      cant_vol_inscritos: 0,
      finicio: "",
      ffin: "",
      id_estado: 0,
      id_emergencia: 0,
    },
  }),

  computed: {
    formTitle() {
      this.max = this.editedItem.cant_vol_requeridos;
      this.getRequirements();
      console.log(this.nuevosRequerimientos)
      return this.editedIndex === -1 ? "Nueva tarea" : "Editar tarea";
    },
  },

  /* Avisa si es que el mensaje debería estar en pantalla o no */
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  /* Mounted ejecuta las lineas al ingresar a la página, en este caso obtiene 
    los datos */
  async mounted() {
    this.getStatusTask();
    await this.getEmergencies();
    this.getTask();
  },

  /* Se definen los métodos */
  methods: {

    uploadRequirements(){
      for(let i=0;i<this.nuevosRequerimientos.length;i++){
        task_skill.id_emehab = this.nuevosRequerimientos[i]
        task_skill.id_tarea = this.editedIndex
        createRequirementes()
      }
    },

    async createRequirementes(){
      const url = "http://localhost:8080/task_skills";
      console.log(task_skill)
      await axios
        .post(url,task_skill)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /* Obtiene las tareas */
    getTask() {
      const url = "http://localhost:8080/tasks";
      axios
        .get(url)
        .then((response) => {
          this.tareas = response.data.sort((a, b) => a.id - b.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getRequirements() {
      const url = 
        "http://localhost:8080/skills/emergencies/" +
        this.editedItem.id_emergencia;
      axios
        .get(url)
        .then((response) => {
          this.requerimientos = response.data.sort((a, b) => a.id - b.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    /* Obtiene los estados de las tareas */
    getStatusTask() {
      const url = "http://localhost:8080/status_tasks";
      axios
        .get(url)
        .then((response) => {
          this.estados = response.data.sort((a, b) => a.id - b.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /* Se obtienen las emergencias */
    async getEmergencies() {
      const url = "http://localhost:8080/emergencies";
      await axios
        .get(url)
        .then((response) => {
          this.emergencias = response.data.sort((a, b) => a.id - b.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /* Se obtiene el nombre de la emergencia o estado, muestra el nombre relacionado al id*/
    getNombre(id, n) {
      if (n == 0) {
        return this.estados[id].descrip;
      } else {
        return this.emergencias[id].nombre;
      }
    },

    /* Se edita la tarea */
    editItem(item) {
      this.editedIndex = item.id;
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    /* Se elimina la tarea */
    deleteItem(item) {
      this.editedIndex = item.id;
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },

    /* Hace la conexión del delete con axios */
    async deleteTask(et) {
      const url = "http://localhost:8080/tasks/" + String(et.id);
      await axios
        .delete(url)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /* Se confirma el delete de un item */
    deleteItemConfirm() {
      this.tareas.splice(this.editedIndex, 1);
      this.closeDelete();
    },

    /* Se cierra el dialogo de editar */
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    /* Se cierra el dialogo de eliminar */
    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    /*conexión con axios para crear la tarea */
    async createTask(et) {
      const url = "http://localhost:8080/tasks";
      await axios
        .post(url, et)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /* conexión con axios para editar la tarea */
    async updateTask(et) {
      const url = "http://localhost:8080/tasks";
      await axios
        .put(url, et)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /* Se confirma la edición de un item */
    save() {
      if (this.editedIndex > -1) {
        this.editedItem.id = this.editedIndex;
        this.updateTask(this.editedItem);
        this.uploadRequirements()
      } else {
        this.editedItem.id = this.tareas.length;
        this.createTask(this.editedItem);
        this.uploadRequirements()
      }
      //this.close()
    },
  },
};
</script>
