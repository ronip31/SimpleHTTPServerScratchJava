//function createBattleField - cria o campo de batalha de acordo. O parâmetro 'qnt' recebe o numero da matriz do jogo

var qntCasas = 0 //Recebe a quantidade de casas que o jogo terá para fazer a distribuição dos barcos
var jogoIniciado = false; //Variavel que determina que o jogo já começou ou não, a fim de evitar a criação de novos tabuleiros
var tiros = 0; //contador do total de tiros dados no jogo
var acertos = 0; //contador de acerto de tiros em barcos

function createBattleField(qnt){
    qntCasas=qnt; //a variavel qntCasas recebe o valor da quantidade de casas que o jogo terá, recebido do HTML index.html
    if(jogoIniciado==false){ //se o jogo nao tiver iniciado
        var newBr = document.createElement('br'); //espaço
        document.body.appendChild(newBr);

        for (var x = 0; x < qnt; x++) {
            for (var y = 0; y < qnt; y++) {
                var item = "div".concat(x.toString(), y.toString()); // cada div deve ter um id unico, formado pela palavra div e seu eixo x e y
                var newDiv = document.createElement('div'); //cria uma nova div
                newDiv.setAttribute("class", "water");
                newDiv.setAttribute("id", item); //atribui ao id da div seu id exclusivo
                newDiv.setAttribute("value", "agua"); //por padrao todo value da div é inicialmente agua
                newDiv.setAttribute("onclick", "letsPlay(this)"); //ao clicar em uma div, é chamado a função letsPlay, passando a div clicada por parametro
                newDiv.style.width = "50px"; //tamanho
                newDiv.style.height = "50px"; //tamanho
                newDiv.style.background = "cyan"; //cor da agua sem clicar
                newDiv.style.display = "inline-block";
                newDiv.style.margin = "1px";
                document.body.appendChild(newDiv); //adiciona a div ao html
            };
            var newBr = document.createElement('br');
            document.body.appendChild(newBr);
        };
        createShips(); //chama a função que cria os barcos no jogo
        jogoIniciado=true; //define que o jogo ja foi iniciado para impedir novos jogos
    }
}


/*
function createShips - cria os navios que serao utilizados no jogo:
um porta avião, que possui 5 casas
um destroyer, que possui 2
dois cruzadores, que possuem 4 casas
*/
function createShips(){
    //a função delega a criação dos barcos para funções exclusivas
    definePortaAviao();
    defineDestroyer();
    defineCruzador1();
    defineCruzador2();
}

function definePortaAviao(){
    console.log("definePortaAviao");
    //define o inicio do barco, indicando a posição inicial
    var inicioPortaAviaoX = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    var inicioPortaAviaoY = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    //escolhe se o barco será desenhado no eixo x ou no eixo y
    var eixoPortaAviao = Math.floor(Math.random() * 2); //0 = x, 1 = y
    //cria o porta avião
    createPortaAviao(inicioPortaAviaoX, inicioPortaAviaoX, eixoPortaAviao);
}

function defineDestroyer(){
    console.log("defineDestroyer");
    //define o inicio do barco, indicando a posição inicial
    var inicioDestroyerX = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    var inicioDestroyerY = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    //escolhe se o barco será desenhado no eixo x ou no eixo y
    var eixoDestroyer = Math.floor(Math.random() * 2); //0 = x, 1 = y
    //como já ha um barco no jogo, é preciso verificar se o novo barco nao ficará sobreposto a ele, entao chama a função que faz a conferencia
    //e dependendo do resultado, novamente definirá outros valores para a posicao inicial atraves de recursividade
    ans = confere(inicioDestroyerX, inicioDestroyerY, eixoDestroyer, 2);
    if (ans==false){
        defineDestroyer();
    } else {
        createDestroyer(inicioDestroyerX, inicioDestroyerY, eixoDestroyer);
    }

}
function defineCruzador1(){
    console.log("defineCruzador1");
    var inicioCruzador1X = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    var inicioCruzador1Y = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    var eixoCruzador1 = Math.floor(Math.random() * 2); //0 = x, 1 = y
    ans = confere(inicioCruzador1X, inicioCruzador1Y, eixoCruzador1, 4);
    if (ans==false){
        defineCruzador1();
    } else {
        createCruzador1(inicioCruzador1X,inicioCruzador1Y, eixoCruzador1);
    }
}
function defineCruzador2(){
    console.log("defineCruzador2");
    var inicioCruzador2X = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    var inicioCruzador2Y = Math.floor(Math.random() * qntCasas) //segue os indices do vetor, de 0-5
    var eixoCruzador2 = Math.floor(Math.random() * 2); //0 = x, 1 = y
    ans = confere(inicioCruzador2X, inicioCruzador2Y, eixoCruzador2, 4);
    if (ans==false){
        defineCruzador2();
    } else {
        createCruzador2(inicioCruzador2X,inicioCruzador2Y, eixoCruzador2);
    }
}

function createPortaAviao(x, y, eixo){
    if(eixo==0){ // (x) o barco fica deitado
        for(i=0; i<5; i++){
            //pega o elemento através da div com as posições sorteadas na função de definir
            var div = document.getElementById("div".concat(x.toString(), i.toString()));
            //muda o seu atributo "value" para o tipo de barco indicado
            div.setAttribute("value", "portaAviao");
        }

    } else { // (y) o barco fica em pe
        for(i=0; i<5; i++){
            var div = document.getElementById("div".concat(i.toString(), y.toString()));
            div.setAttribute("value", "portaAviao");
        }
    }
}

function createDestroyer(x, y, eixo){
    if(eixo==0){ // (x) o barco fica deitado
        console.log("createDestroyer");
        for(i=0; i<2; i++){
            var div = document.getElementById("div".concat(x.toString(), i.toString()));
            //div.style.background = 'black';
            div.setAttribute("value", "destroyer");
        }
    } else { // (y) o barco fica em pe
        for(i=0; i<2; i++){
            var div = document.getElementById("div".concat(i.toString(), y.toString()));
            //div.style.background = 'black';
            div.setAttribute("value", "destroyer");
        }
    }
}

function createCruzador1(x, y, eixo){
    console.log('createCruzador1');
    if(eixo==0){ // (x) o barco fica deitado
        for(i=0; i<4; i++){
            var div = document.getElementById("div".concat(x.toString(), i.toString()));
            //div.style.background = 'green';
            div.setAttribute("value", "cruzador1");
        }
    } else { // (y) o barco fica em pe
        for(i=0; i<4; i++){
            var div = document.getElementById("div".concat(i.toString(), y.toString()));
            //div.style.background = 'green';
            div.setAttribute("value", "cruzador1");
        }
    }
}

function createCruzador2(x, y, eixo){
    console.log("createCruzador2");
    if(eixo==0){ // (x) o barco fica deitado
        for(i=0; i<4; i++){
            var div = document.getElementById("div".concat(x.toString(), i.toString()));
            //div.style.background = 'purple';
            div.setAttribute("value", "cruzador2");
        }
    } else { // (y) o barco fica em pe
        for(i=0; i<4; i++){
            var div = document.getElementById("div".concat(i.toString(), y.toString()));
            //div.style.background = 'purple';
            div.setAttribute("value", "cruzador2");
        }
    }
}

function confere(x, y, eixo, tamanho){
    //confere se o barco ficará com alguma peça sobreposta a outro barco que já está no jogo
    console.log("conferindo")
    if(eixo==0){ // (x) o barco fica deitado
        for(i=0; i<tamanho; i++){
            //se alguma peça ficar em uma posição que o value seja diferente de "agua", é preciso novamente sortear posições
            var div = document.getElementById("div".concat(x.toString(), i.toString()));
            if (div.getAttribute("value") != 'agua'){
                return false; //retorna false para que o jogo sorteie novas posições
            }
        }
    } else { // (y) o barco fica em pe
        for(i=0; i<tamanho; i++){
            var div = document.getElementById("div".concat(i.toString(), y.toString()));
            if (div.getAttribute("value") != 'agua'){
                return false;
            }
        }
    }
    return true;
}

//função que recebe o click do jogador sobre uma das divs e informa se ele acertou a agua ou algum barco
function letsPlay(div){ //recebeu o objeto clicado "this"
    tipo = div.getAttribute("value");  //pega o value para conferir se é agua ou um barco
    switch (tipo){
        case "portaAviao":
            alert("Atingiu Porta Avião");
            div.style.background = 'red'; //altera a cor da div para indicar que é um barco
            acertos+=1; //adiciona +1 ao total de acertos
            div.setAttribute("value", "portaAviaoX");
            break;
        case "destroyer":
            alert("Atingiu Destroyer");
            div.style.background = 'green';
            acertos+=1;
            div.setAttribute("value", "destroyerX");
            break;
        case "cruzador1":
            alert("Atingiu Cruzador");
            div.style.background = 'yellow';
            acertos+=1;
            div.setAttribute("value", "cruzador1X");
            break;
        case "cruzador2":
            alert("Atingiu Cruzador");
            div.style.background = 'purple';
            acertos+=1;
            div.setAttribute("value", "cruzador2X");
            break;
        case "agua":
            alert("Tiro na água"); //casos tenha errado
            div.style.background = 'blue'; //muda a tonalidade da div para um tom mais escuro de azul
            break;
    }
    tiros+=1; //adiciona +1 ao total de tiros

    if(acertos>=15){ //caso tenha acertado + de 15 tiros (a soma de todos os pedaços de todos os barcos)
        alert("Parabéns! Você ganhou com "+tiros+" tiros!");
        if (confirm('Deseja continuar jogando?')) { //pergunta se o usuario deseja jogar uma nova batalha
            location.reload(); //em caso afirmativo, reinicia a pagina
        }
    }
}