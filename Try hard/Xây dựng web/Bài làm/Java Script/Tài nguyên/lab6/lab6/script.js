//Receipt
var quantity = document.getElementsByClassName('quantity');
var cost = document.getElementsByClassName('cost');
var unitPrice = document.getElementsByClassName('unit-price');
var totalCost = document.getElementById("total-cost");
totalCost.textContent = "0 vnd";

var costValue = []; //Mảng lưu trữ "Thành tiền của mỗi sản phẩm"

for (let i = 0; i < quantity.length; i++) {
	quantity[i].onchange = function () {
		//Mỗi lần Số lượng ở một sản phẩm thay thay đổi, thực hiện function sau đây

		//Tính giá trị sản phẩm, lưu trữ vào phần tử tương ứng của mảng "Thành tiền của mỗi sản phẩm"
		costValue[i] = quantity[i].value * parseInt(unitPrice[i].textContent);

		//Gán tổng tiền về 0 và tính lại tổng tiền;
		let totalCostValue = 0;
		costValue.forEach(element => {
			totalCostValue += element;
		});

		//Hiển thị theo định dạng tiền tệ
		cost[i].textContent = costValue[i] + " vnd";
		cost[i].textContent = cost[i].textContent.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		totalCost.textContent = totalCostValue + " vnd";
		totalCost.textContent = totalCost.textContent.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
}
