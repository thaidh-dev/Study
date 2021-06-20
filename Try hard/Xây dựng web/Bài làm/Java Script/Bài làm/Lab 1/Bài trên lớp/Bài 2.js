var a = Number(prompt("Cạnh a", ""));
var b = Number(prompt("Cạnh b", ""));
var c = Number(prompt("Cạnh c", ""));
var p = Number((a+b+c)/2);
document.write("S = "+Math.sqrt(p*(p-a)*(p-b)*(p-c)));