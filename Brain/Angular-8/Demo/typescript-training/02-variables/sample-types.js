var found = true;
var grade = 88.6;
var firstName = "thái";
var lastName = "dương";
console.log(found);
console.log(grade);
console.log(firstName);
console.log("Hi " + firstName + " " + lastName);
var date = new Date('1696-12-02');
var date2 = new Date();
date2.setDate(date.getDate() + 365);
console.log(date);
console.log(date2);
console.log(date2 < date);
console.log(date.toISOString());
console.log(date.toUTCString());
console.log(date.toLocaleDateString());
console.log(date.toJSON());
console.log(date.toLocaleString());
console.log(date.toDateString());
// tsc tenFile.ts
// node tenFile.js
