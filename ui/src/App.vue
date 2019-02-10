<template>
  <b-container class="text-center">
    <h1 style="margin-top:10px">Customers</h1>

    <app-customer
      v-for="customer of customers"
      v-bind:key="customer.id"
      :id="customer.id"
      :name="customer.name"
      :surname="customer.surname"
    ></app-customer>

    <h2 style="margin-top:20px">Create new customer</h2>
    <div>
      <b-form inline>
        <label class="sr-only" for="inlineFormInputName2">Name</label>
        <b-input
          class="mb-2 mr-sm-2 mb-sm-0"
          id="inlineFormInputName2"
          :state="customerName.length > 2 ? true : false"
          v-model="customerName"
          placeholder="Name"
        />
        <label class="sr-only" for="inlineFormInputGroupUsername2">Surname</label>
        <b-input-group left="@" class="mb-2 mr-sm-2 mb-sm-0">
          <b-input
            id="inlineFormInputGroupUsername2"
            v-model="customerSurname"
            :state="customerSurname.length > 2 ? true : false"
            placeholder="Surname"
          />
        </b-input-group>
        <b-button variant="primary" @click="createCustomer()">Create Customer</b-button>
      </b-form>
    </div>
  </b-container>
</template>

<script>
import axios from "axios";
import Customer from "./Customer.vue";

export default {
  components: {
    appCustomer: Customer
  },
  data() {
    return {
      customerName: "",
      customerSurname: "",
      customers: []
    };
  },
  created() {
    this.$http
      .get("customers")
      .then(response => {
        this.customers = response.data;
      })
      .catch(e => {
        console.error(e);
      });
  },
  methods: {
    createCustomer() {
      if (this.customerName.length > 2 && this.customerSurname.length > 2) {
        this.$http
          .post("customers", {
            name: this.customerName,
            surname: this.customerSurname
          })
          .then(response => {
            this.customers.push(response.data);
          })
          .catch(e => {
            console.error(e);
          });
      } else {
        alert("Invalid Name & Surname");
      }
    }
  }
};
</script>
