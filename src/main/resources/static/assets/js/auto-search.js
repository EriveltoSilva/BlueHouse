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
    document.getElementById("id").value = "";
    document.getElementById("bi").value = "";
    document.getElementById("endereco").value = "";
    document.getElementById("email").value =  "";
    document.getElementById("contacto").value = "";
    document.getElementById("dataNascimento").value = "";
    makeSearch("all");
}

function select(element) {
    for (let index = 0; index < persons.length; index++) {
        if (persons[index].nomeCompleto == element.textContent) {
            inputBox.value = persons[index].nomeCompleto;
            console.log("Pessoa com Id:",document.getElementById("id").value);
            console.log("Pessoa com dataNascimento:",document.getElementById("dataNascimento").value);
            document.getElementById("id").value = persons[index].id;
            document.getElementById("bi").value = persons[index].bi;
            document.getElementById("endereco").value = persons[index].endereco;
            // document.getElementById("email").value = persons[index].email;
            document.getElementById("contacto").value = persons[index].contacto;
            document.getElementById("dataNascimento").value = persons[index].dataNascimento;
            
            document.getElementById("generoMasculino").checked =(persons[index].genero=="M");
            document.getElementById("generoFeminino").checked =(persons[index].genero=="F");
            
        }
    }
}

async function makeSearch(word) {
    await fetch("/ocorrencias/get-reportante?nomeCompleto=" + word, {
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
            options.innerHTML = emptyArray ? emptyArray : '<p> Ooops! Pessoa não encontrada!</p>';
        })
        .catch((error) => {
            console.error('Erro:', error);
            alert("Erro");
        });
}
