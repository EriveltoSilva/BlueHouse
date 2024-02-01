const inputBox = document.getElementById("nomeCompleto");
const options = document.querySelector(".my-content-box");
let persons = [];

window.addEventListener("load", ()=>{
    makeSearch("all");
});

inputBox.addEventListener("keyup", (e) => {
    let userData = e.target.value;
    if (userData)
        makeSearch(userData);
    else
        cleanFields();

});

function cleanFields() {
    inputBox.value = "";
    document.getElementById("idFuncionario").value = "";
    makeSearch("all");
}

function select(element) {
    for (let index = 0; index < persons.length; index++) {
        if (persons[index].nomeCompleto == element.textContent) {
            inputBox.value = persons[index].nomeCompleto;
            console.log("Pessoa com Id:",document.getElementById("idFuncionario").value);
            document.getElementById("idFuncionario").value = persons[index].id;
            // alert(document.getElementById("idFuncionario").value)
            
        }
    }
}

async function makeSearch(word) {
    await fetch("/ocorrencias/get-funcionario?nomeCompleto=" + word, {
        method: 'GET', headers: { 'Content-Type': 'application/json' },
    })
        .then(resp => resp.json())
        .then(resp => {
            // console.log(resp);
            let emptyArray = [];
            persons = resp;
            persons.forEach(element => {
                emptyArray.push(element.nomeCompleto);
            });
            emptyArray = emptyArray.map((data) => {
                return data = '<li onclick="select(this)" class="p-2">' + data + '</li>'
            }).join('');
            options.innerHTML = emptyArray ? emptyArray : '<p> Ooops! Funcionário não encontrado!</p>';
        })
        .catch((error) => {
            console.error('Erro:', error);
            alert("Erro");
        });
}
