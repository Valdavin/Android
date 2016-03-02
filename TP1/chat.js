function Chat (nom, couleur, age) {
    this.nom = nom;
    this.couleur = couleur;
    this.age = age;
}

Chat.prototype.miauler = function () {
    console.log("miaou");
};

function ChatBizarre (nom, couleur, age) {
    Chat.call(this, nom, couleur, age);
}

ChatBizarre.prototype = new Chat();

ChatBizarre.prototype.miauler = function () {
    console.log("ouaf");
};

var miauler = new Chat("Jimmy","bleu",15);
var ouaf = new ChatBizarre("fsdf","sdfsdf",13);

miauler.miauler();
ouaf.miauler();

console.log(miauler)
console.log(ouaf)
