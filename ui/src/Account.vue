<template>
  <div role="tablist">
    <b-card no-body class="mb-1">
      <b-card-header header-tag="header" class="p-1" role="tab">
        <b-btn block href="#" v-b-toggle="'account'+id" variant="info">
          <span>ID: {{ id }}</span>
          <span>Balance: {{ balance + deltaBalance }}</span>
          <span>Transactions</span>
          <span>Initial Balance: {{ initialBalance }}</span>
        </b-btn>
      </b-card-header>
      <b-collapse
        :id="'account'+ id"
        accordion="my-account-accordion"
        role="tabpanel"
        @show="$emit('accountShown'); onExpand();"
        @hide="$emit('accountHidden')"
      >
        <b-card-body>
          <h3>Transactions:</h3>

          <app-transaction
            v-for="transaction of transactions"
            v-bind:key="transaction.id"
            :id="transaction.id"
            :accountId="transaction.accountId"
            :transactionAmount="transaction.transactionAmount"
            :transactionDate="transaction.transactionDate"
          ></app-transaction>

          <b-form inline>
            <label class="sr-only" for="transactionAmount">Transaction Amount</label>
            <b-input
              class="mb-2 mr-sm-2 mb-sm-0"
              id="transactionAmount"
              v-model="transactionAmount"
              placeholder="Transaction Amount"
            />
            <b-button variant="primary" @click="createTransaction()">Create New Transaction</b-button>
          </b-form>
        </b-card-body>
      </b-collapse>
    </b-card>
  </div>
</template>

<script>
import Transaction from "./Transaction.vue";
export default {
  components: {
    appTransaction: Transaction
  },
  props: {
    id: Number,
    customerId: Number,
    initialBalance: Number,
    balance: Number
  },
  data() {
    return {
      deltaBalance: 0,
      transactionAmount: 0,
      transactions: []
    };
  },
  methods: {
    onExpand() {
      this.$http
        .get("accounts/" + this.id + "/transactions")
        .then(response => {
          this.transactions = response.data;
        })
        .catch(e => {
          console.error(e);
        });
    },
    createTransaction() {
      if (Number(this.transactionAmount) != 0) {
        this.$http
          .post("accounts/transactions", {
            accountId: this.id,
            transactionAmount: Number(this.transactionAmount)
          })
          .then(response => {
            this.transactions.push(response.data);
            this.deltaBalance += response.data.transactionAmount;
            this.transactionAmount = 0;
          })
          .catch(e => {
            this.transactionAmount = 0;
            console.error(e.response.data.errorMsg);
            alert(e.response.data.errorMsg);
          });
      } else {
        alert("Enter a value for transaction amount");
      }
    }
  }
};
</script>