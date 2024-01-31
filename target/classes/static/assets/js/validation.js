document.addEventListener("DOMContentLoaded", function() {
    
    var nomeCompletoInput = document.getElementById("nomeCompleto");
    var contactoInput = document.getElementById("contacto");
    var biInput = document.getElementById("bi");
    var emailInput = document.getElementById("email");
    
    nomeCompletoInput.addEventListener("input", function(event) {
        var inputValue = event.target.value;
        event.target.value = inputValue.replace(/[^a-zA-Z\s]/g, '');
    });

    contactoInput.addEventListener("input", function(event) {
        var inputValue = event.target.value;
        event.target.value = inputValue.replace(/\D/g, '').slice(0, 9);
    });

    biInput.addEventListener("input", function(event){
        var inputValue = event.target.value;
        inputValue = inputValue.replace(/[^a-zA-Z0-9]/g, '').slice(0, 14); 
        event.target.value = inputValue;

        if (inputValue.length === 14) {
            validarBI(inputValue);
        }
    });
          
    function validarBI(input_bi) {
        const biValidar = /^\d{9}[A-Z]{2}\d{3}$/;
        if (!biValidar.test(input_bi)) {
            alert("BI inválido.");
        }
    }

});
