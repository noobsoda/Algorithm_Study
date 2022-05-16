
const h1 = document.querySelector(".paragraph");
const p = document.getElementById("text");

h1.textContent = "자바스크립트로 변경중"

p.textContent = "겟엘리먼트바디";
console.log(p.textContent);


let test = 10;
console.log(test);
test = "테스트"
console.log(test);

//const data = prompt("하이요!");
//console.log(`문자열 중간에 ${data} 삽입하기`);

let data1;
data1 = null;
console.log(data1);

//== 추상적 비교 자료형 일치하지 않아도 같으면 true '1' ==  1 true
//=== 엄격한 비교 자료형까지 일치해야 true '1' === 1 false

//메시지 보내서 true, false 반환 confirm("메시지!");

for(let i = 0; i < 4; i++){
    console.log("확인");
}
console.log("취소");


//함수 선언식 2가지


//함수 선언식은 위에 있어도 호이스팅이 가능하다라고 함
function sayHello(let, number){
    number = 3+ 3;
    return number;
}
//함수 표현식은 호출문 보다 밑에 있어야 불러올수 있음
const sayBye = function(){
    console.log("good bye~~");
}

console.log(sayHello());
sayBye();

const inputType = document.querySelector("#typing");
// const inputClick = document.querySelector("#push");

const handleTyping = function(){
    console.log("타이핑 되고 있어요!");
}

inputType.onkeydown = handleTyping;

// inputClick.onclick = function(){
//     console.log("클릭되고 있어요!");
// }

const btn1 = document.getElementById("push1");
const btn2 = document.getElementById("push2");
const btn3 = document.getElementById("push3");

const handleClick = function(event){
    console.log(event.target);
}

//이벤트 리스너를 쓰면 이벤트를 여러개 등록할 수 있다.
btn2.addEventListener('click', handleClick);
