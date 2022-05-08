const svg = d3.select("body").append("svg")
    .attr("width", 960)
    .attr("height", 500)
    .style("background", "greenyellow");

const text = svg.append("text")
    .attr("x", 0)
    .attr("y", 50)
    .attr("transform", "translate(50, 0) rotate(45)")
    // .attr("dy", ".35em")
    // .attr("text-anchor", "middle")
    .style("font", "300 50px Helvetica Neue")
    .text("Hello, getBBox!");

const bbox = text.node().getBBox();
const bcr = text.node().getBoundingClientRect();
// pytago
const distanceXFristToXSecond = Math.sqrt(Math.pow(bbox.width, 2) / 2);
const distanceYFristToYSecond = distanceXFristToXSecond;
const distanceXFourthToXThird = distanceXFristToXSecond;
const distanceYFourthToYThird = distanceXFristToXSecond;

// follow clockwise
let first = {
    x: bcr.right - distanceXFristToXSecond,
    y: bcr.top,
}
let second = {
    x: bcr.right,
    y: distanceYFristToYSecond + bcr.top,
}
let third = {
    // pytago
    x: distanceXFourthToXThird + bcr.left,
    y: bcr.bottom
};
let fourth = {
    x: bcr.left,
    y: bcr.bottom - distanceYFourthToYThird
}

console.log(bbox);
console.log(bcr);
console.log(first);
console.log(second);
console.log(third);
console.log(fourth);
