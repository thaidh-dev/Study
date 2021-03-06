let chart;
let originalData;

nv.addGraph(function () {
    chart = nv.models.discreteBarChart()
        .x(function (d) { return d.label })    //Specify the data accessors.
        .y(function (d) { return d.value })
        .staggerLabels(true)    //Too many bars and not enough room? Try staggering labels.
        // .tooltips(false)        //Don't show tooltips
        .showValues(true)       //...instead, show the bar value right on top of each bar.
        // .transitionDuration(350)
        ;

    d3.select('#chart svg')
        .datum(exampleData())
        .call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
});

//Each bar represents a single discrete quantity.
function exampleData() {
    return [
        {
            key: "Cumulative Return",
            values: [
                {
                    "label": "A Label",
                    "value": -29.765957771107
                },
                {
                    "label": "B Label",
                    "value": 0
                },
                {
                    "label": "C Label",
                    "value": 32.807804682612
                },
                {
                    "label": "D Label",
                    "value": 196.45946739256
                },
                {
                    "label": "E Label",
                    "value": 0.19434030906893
                },
                {
                    "label": "F Label",
                    "value": -98.079782601442
                },
                {
                    "label": "G Label",
                    "value": -13.925743130903
                },
                {
                    "label": "H Label",
                    "value": -5.1387322875705
                }
            ]
        }
    ]

}


function filter() {
    const textTag = d3.select('g.nv-bar text');
    textTag.style("font-size", "34px");
    let textLength = textTag.node().getComputedTextLength();
    let text = textTag.text();

    const g = d3.select('g.nv-bar rect').node();
    let width = g.getBoundingClientRect().width;
    while (textLength > width && text.length > 0) {
        text = text.slice(0, -1);
        textTag.text(text + '...');
        textLength = textTag.node().getComputedTextLength();
    }
}
