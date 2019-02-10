<template>
  <b-list-group class="text-center" bg-variant="info" text-variant="white" >
    <b-list-group-item>
      <b>Date:</b> {{ transactionDate }}
      <b> Amount:</b> {{ transactionAmount }}
    </b-list-group-item>
  </b-list-group>
</template>

<script>
export default {
  props: {
    id: Number,
    accountId: Number,
    transactionAmount: Number,
    transactionDate: String
  }
};
</script>