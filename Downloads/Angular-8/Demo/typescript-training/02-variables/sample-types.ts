let found: boolean = true;
let grade: number = 88.6;
let firstName: string = "thái";
let lastName: string = "dương";

console.log(found);
console.log(grade);
console.log(firstName);
console.log(`Hi ${firstName} ${lastName}`);

const date = new Date('1696-12-02');
const date2 = new Date();
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