let chart;
let originalData;

nv.addGraph(function () {
    chart = nv.models.multiBarChart()
        .reduceXTicks(true)   //If 'false', every single x-axis tick label will be rendered.
        .rotateLabels(0)      //Angle to rotate x-axis labels.
        .showControls(true)   //Allow user to switch between 'Grouped' and 'Stacked' mode.
        .groupSpacing(0.1)    //Distance between each group of bars.
        ;

    chart.xAxis.axisLabel('HELLO WORLD');
    chart.xAxis.tickFormat(d3.format(',f'));
    chart.xAxis.axis.tickSize(15, 0);

    chart.yDomain([0, 20]);
    chart.yAxis.tickFormat(d3.format(',.1f'));
    chart.yAxis.fontSize(20);
    chart.yAxis.ticks(10);

    d3.select('#chart1 svg')
        .datum(exampleData())
        .transition().duration(5000)
        .call(chart);


    nv.utils.windowResize(chart.update);

    return chart;
});

//Generate some nice data.
function exampleData() {
    return stream_layers(7, 10 + Math.random() * 100, .1).map(function (data, i) {
        return {
            key: 'Stream #' + i,
            values: data
        };
    });
}

function filter() {
    let data = d3.select('#chart1 svg').datum();
    const dataToDelete = document.getElementById('filter').value;
    if (dataToDelete !== '') {
        data.splice(dataToDelete, 1);
    }
    d3.select('#chart1 svg').datum(data).transition().duration(5000).call(chart);
}
