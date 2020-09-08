<template>
<div id="form-div">
        <p1> 모아보자 </p1>
    <form class="form" id="form1" @submit="onSubmit">
        <p5> 모집장소 </p5>
        <p class="name">
            <input name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name" id="name" />
        </p>
        <p5>모집인원</p5>
        <select class="people" name = "people">
              <optgroup label = "모집인원">
                <option value = "1">1명</option>
                <option value = "2">2명</option>
                <option value = "3">3명</option>
                <option value = "4">4명</option>
              </optgroup>         
        </select>
        <p5 id="a">마감시간</p5>
        <p class="time"><input type="time"></p>
        <p5>수령장소</p5>
        <p class="email">
            <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email" />
        </p>
        <p5>배달금액</p5>
        <p class="email">
            <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email" />
        </p>
        <p5>설명</p5>
        <p class="text">
            <textarea name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Comment"></textarea>
        </p>
        <div class="submit">
            <input type="submit" value="SEND" id="button-blue"/>
            <div class="ease"></div>
        </div>
    </form>
</div>
</template>

<script>
  export default {
  }
</script>

<style scoped>
p5 {
  color : white;
  font-weight: bold;
}
#a {
  display: inline;
}

html{
    height:100%;
    
}
#map{
  width:600px;
  height:500px;
}

#form-div {
    margin-left: 100px;
    background-color: #bbb;
    padding:35px 35px 50px;
    width: 450px;
    left: 50%;
    position: relative;
    margin-top:30px;
    -moz-border-radius: 7px;
    -webkit-border-radius: 7px;
}

.feedback-input {
    color:#3c3c3c;
    font-family: Helvetica, Arial, sans-serif;
    font-weight:500;
    font-size: 18px;
    border-radius: 0;
    line-height: 22px;
    background-color: #ffffff;
    margin-bottom: 10px;
    width:100%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    -ms-box-sizing: border-box;
    box-sizing: border-box;
    border: 3px solid rgba(0,0,0,0);
}

.feedback-input:focus{
    background: #fff;
    box-shadow: 0;
    /*border: 3px solid #3498db;*/
    border-color: #3498db;
    color: #3498db;
    outline: none;
    /*padding: 13px 13px 13px 54px;*/
}

.focused {
    color:#30aed6;
    border:#30aed6 solid 3px;
}

/* Icons */
#name{
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

#email{
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

#comment{
    background-size: 20px 20px;
    background-position: 11px 8px;
    background-repeat: no-repeat;
}

textarea {
    width: 100%;
    height: 150px;
    line-height: 150%;
    resize:vertical;
}

input:hover, textarea:hover,
input:focus, textarea:focus {
    background-color:white;
}

#button-blue{
    font-family: 'Montserrat', Arial, Helvetica, sans-serif;
    float:left; /* 플롯 중요(::after 가상요소 이용)*/
    width: 100%;
    border: #fbfbfb solid 4px;
    cursor:pointer;
    background-color: #3498db;
    color:white;
    font-size:24px;
    padding-top:22px;
    padding-bottom:22px;
    -webkit-transition: all 0.3s;
    -moz-transition: all 0.3s;
    transition: all 0.3s;
    margin-top:-4px;
    font-weight:700;

}

#button-blue:hover{
    background-color: rgba(0,0,0,0);
    color: #0493bd;
}

.ease {
    width: 0;
    height: 74px;
    background-color: #fbfbfb;
    -webkit-transition: .3s ease;
    -moz-transition: .3s ease;
    -o-transition: .3s ease;
    -ms-transition: .3s ease;
    transition: .3s ease;
}

.submit:hover .ease{
    width:100%;
    background-color:white;
}

.cover{
  text-align: center;
}


</style>