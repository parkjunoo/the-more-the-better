<!--<template>-->
<!--    &lt;!&ndash; navigation bar &ndash;&gt;-->
<!--    <nav class="navbar navbar-default">-->
<!--        <div class="container-fluid">-->
<!--            <a class="navbar-brand"><i class="glyphicon glyphicon-bullhorn"></i> Vue Events Bulletin Board</a>-->
<!--        </div>-->
<!--    </nav>-->

<!--    &lt;!&ndash; main body of our application &ndash;&gt;-->
<!--    <div class="container" id="events">-->

<!--        &lt;!&ndash; add an event form &ndash;&gt;-->
<!--        <div class="col-sm-6">-->
<!--            <div class="panel panel-default">-->
<!--                <div class="panel-heading">-->
<!--                    <h3>Add an Event</h3>-->
<!--                </div>-->
<!--                <div class="panel-body">-->

<!--                    <div class="form-group">-->
<!--                        <input class="form-control" placeholder="Event Name" v-model="event.mem_name">-->
<!--                    </div>-->

<!--                    <div class="form-group">-->
<!--                        <textarea class="form-control" placeholder="Event Description" v-model="event.description"></textarea>-->
<!--                    </div>-->

<!--                    <div class="form-group">-->
<!--                        <input type="date" class="form-control" placeholder="Date" v-model="event.date">-->
<!--                    </div>-->

<!--                    <button class="btn btn-primary" v-on:click="addEvent">Submit</button>-->

<!--                </div>-->

<!--            </div>-->
<!--        </div>-->

<!--        &lt;!&ndash; show the events &ndash;&gt;-->
<!--        <div class="col-sm-6">-->
<!--            <button class="btn btn-primary" @click="order = order * -1">Reverse Order</button>-->
<!--            <div class="list-group">-->

<!--                <a href="#" class="list-group-item" v-for="event in events | orderBy 'date' order">-->
<!--                    <h4 class="list-group-item-heading">-->
<!--                        <i class="glyphicon glyphicon-bullhorn"></i>-->
<!--                        {{ event.mem_name }}-->
<!--                    </h4>-->

<!--                    <h5>-->
<!--                        <i class="glyphicon glyphicon-calendar" v-if="event.date"></i>-->
<!--                        {{ event.date }}-->
<!--                    </h5>-->

<!--                    <p class="list-group-item-text" v-if="event.description">{{ event.description }}</p>-->

<!--                    <button class="btn btn-xs btn-danger" v-on:click="deleteEvent($index)">Delete</button>-->
<!--                </a>-->

<!--            </div>-->

<!--        </div>-->
<!--    </div>-->

<!--</template>-->

<!--<script>-->
<!--    // app js-->

<!--    new Vue({-->
<!--        el: "#events",-->

<!--        data: {-->
<!--            order: 1,-->
<!--            event: { mem_name: "", description: "", date: "" },-->
<!--            events: []-->
<!--        },-->

<!--        ready: function () {-->
<!--            this.fetchEvents();-->
<!--        },-->

<!--        methods: {-->
<!--            fetchEvents: function () {-->
<!--                var events = [-->
<!--                    {-->
<!--                        id: 1,-->
<!--                        mem_name: "Film Premiere",-->
<!--                        description: "Go watch that movie.",-->
<!--                        date: "2016-09-11"-->
<!--                    },-->
<!--                    {-->
<!--                        id: 2,-->
<!--                        mem_name: "Hangover",-->
<!--                        description: "It's your own fault.",-->
<!--                        date: "2016-11-01"-->
<!--                    },-->
<!--                    {-->
<!--                        id: 3,-->
<!--                        mem_name: "Add Firebase Support",-->
<!--                        description: "Free, hosted database, none of this JS BS.",-->
<!--                        date: "2016-09-15"-->
<!--                    },-->
<!--                    {-->
<!--                        id: 4,-->
<!--                        mem_name: "Bonfire Night",-->
<!--                        description: "Hotdogs and pyrotechnics.",-->
<!--                        date: "2016-11-04"-->
<!--                    },-->
<!--                    {-->
<!--                        id: 5,-->
<!--                        mem_name: "Halloween",-->
<!--                        description: "Get my costume on, get my drink on.",-->
<!--                        date: "2016-10-31"-->
<!--                    }-->
<!--                ];-->

<!--                this.$set("events", events);-->
<!--            },-->

<!--            addEvent: function () {-->
<!--                if (this.event.mem_name) {-->
<!--                    this.events.push(this.event);-->
<!--                    this.event = { mem_name: "", description: "", date: "" };-->
<!--                }-->
<!--            },-->

<!--            deleteEvent: function (index) {-->
<!--                if (confirm("Are you sure you want to delete this event?")) {-->
<!--                    this.events.splice(index, 1);-->
<!--                }-->
<!--            }-->
<!--        }-->
<!--    });-->

<!--</script>-->

<!--<style scoped>-->
<!--    .btn.btn-primary {-->
<!--    }-->
<!--</style>-->
