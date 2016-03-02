var tab = new Array();

function sum(tab) {
    var sum = 0;
    for (var i of tab) {
        sum += i;
    }
    return sum;
}
function moyenne(tab) {
    return sum(tab)/tab.length;
}
function nb0(tab) {
    var sum = 0;
    for (var i of tab) {
        if (i == 0) {
            sum++;
        }
     }
    return sum;
}

    do {
        i=parseInt(prompt("entrez une valeur"));
        if (i != -1) {
            tab.push(i);
        }
    } while (i != -1);
document.getElementsByTagName("h1")[0].innerHTML = "sum = " + sum(tab) + "Moyenne = " + moyenne(tab) + " Nb de 0 : " + nb0(tab);
