<template>
    <div class="hello">
        <IndexSlideBanner></IndexSlideBanner>
        <IndexRanking></IndexRanking>
        <IndexWaitingList></IndexWaitingList>
        <IndexEventList></IndexEventList>
        <IndexFooter></IndexFooter>
    </div>
</template>

<script>
    import IndexSlideBanner from './IndexSlideBanner';
    import IndexRanking from './IndexRanking';
    import IndexWaitingList from './IndexWaitingList.vue';
    import IndexEventList from './IndexEventList';
    import IndexFooter from './IndexFooter';
    export default {
        name: 'Main',
        components:{

            IndexSlideBanner,
            IndexRanking,
            IndexWaitingList,
            IndexEventList,
            IndexFooter
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>

