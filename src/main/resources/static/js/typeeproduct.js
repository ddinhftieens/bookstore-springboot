const type = {11:"Tiểu thuyết",12:"Trinh thám",13:"Hồi kí",14:"Kinh điển",21:"Truyện tranh",22:"Truyện cổ tích",31:"Thiên văn",32:"Lịch sử",33:"Sinh vật học",34:"Tâm lí học",35:"Hiện tượng tự nhiên",41:"Kinh nghiệm",42:"Hướng nghiệp",43:"Sáng tạo - Tư duy",51:"Tiếng Anh",52:"Tiếng Nhật",53:"Tiếng Trung Quốc"};;
var typee = document.getElementById("typee").innerText;
document.getElementById("typee").innerHTML = type[typee];

// var quantity = document.getElementById("state").innerText;
// function quantityy() {
//     if(quantity>0){
//         return "Còn hàng";
//     }
//     else{
//         return "Tạm thời hết hàng";
//     }
// }
// document.getElementById("state").innerHTML = quantityy();


// console.log(type[14]);
// const object = {1:"Tien",2:"Nguyen",3:"Thanh",4:"Tra"};
// var s = 1;
// console.log(object[s]);