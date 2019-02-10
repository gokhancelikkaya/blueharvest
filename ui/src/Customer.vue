<template>
  <div role="tablist">
    <b-card no-body class="mb-1">
      <b-card-header header-tag="header" class="p-1" role="tab">
        <b-btn block href="#" v-b-toggle="''+id" variant="info">{{ name + ' ' + surname }}</b-btn>
      </b-card-header>
      <b-collapse
        :id="''+ id"
        accordion="my-accordion"
        role="tabpanel"
        @show="onExpand"
      >
        <b-card-body>
          <h3>{{ name + ' ' + surname }} Accounts:</h3>

          <app-account
            v-for="account of accounts"
            v-bind:key="account.id"
            :id="account.id"
            :customerId="account.customerId"
            :initialBalance="account.initialBalance"
            :balance="account.balance"
            @accountShown="showCreateButton=false"
            @accountHidden="showCreateButton=true"
          ></app-account>

          <b-form inline v-show="showCreateButton">
            <label class="sr-only" for="initialBalance">Initial Balance</label>
            <b-input
              class="mb-2 mr-sm-2 mb-sm-0"
              id="initialBalance"
              v-model="initialBalance"
              placeholder="Initial Balance"
            />
            <b-button variant="primary" @click="createAccount()">Create New Account</b-button>
          </b-form>
        </b-card-body>
      </b-collapse>
    </b-card>
  </div>
</template>

<script>
import Account from "./Account.vue";
export default {
  components: {
    appAccount: Account
  },
  data() {
    return {
      showCreateButton: true,
      initialBalance: "",
      accounts: []
    };
  },
  props: {
    id: Number,
    name: String,
    surname: String
  },
  methods: {
    onExpand() {
      this.$http
        .get("customers/" + this.id + "/accounts")
        .then(response => {
          this.accounts = response.data;
        })
        .catch(e => {
          console.error(e);
        });
    },
    createAccount() {
      this.$http
        .post("accounts", {
          customerId: this.id,
          initialBalance: this.initialBalance
        })
        .then(response => {
          this.accounts.push(response.data);
          this.initialBalance = 0;
        })
        .catch(e => {
          console.error(e.response.data.errorMsg);
          alert(e.response.data.errorMsg);
        });
    }
  }
};
</script>