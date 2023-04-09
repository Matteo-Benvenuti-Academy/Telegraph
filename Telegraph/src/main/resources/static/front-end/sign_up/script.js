const urlSignUp = 'http://localhost:8080/Telegraph/signUp';

const emailValidatioRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})/;
const spacevalidationRegex = /^(.*\s+.*)+$/;

function checkSignOut(){
    $("#formAlert").hide("");

    let email = $("#emailInput").val()
    let username = $("#usernameInput").val()
    let pass = $("#passwordInput").val()

    if(!emailValidatioRegex.test(email))
        return invalidEmail();
    if(username === "" || spacevalidationRegex.test(username))
        return invalidUsername();
    if(pass === "" || spacevalidationRegex.test(username))
        return invalidPass();

    fetch(urlSignUp,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email,
                username,
                pass,
            })
        })
        .then(result => result.json())
        .then(response => {
            if(response.status === "ok"){
                signUp();
            }else{
                let invalidInput = response.data;  
                checkInvalidInput(invalidInput,email,username);
            }
        })
        .catch(errore => signUpFail(errore));

}

function checkInvalidInput(invalidInput,email,username){
    if(invalidInput === "invalid Email")
        return invalidEmail();
    if(invalidInput === "duplicate Email") 
        return invalidEmail(email)
    if(invalidInput === "invalid Username")
        return invalidUsername()
    if(invalidInput == "duplicate Username")
        return invalidUsername(username)
    if(invalidInput == "invalid Password")
        return invalidPass()
    
    signUpFail(`invalidInput not valid: ${invalidInput}`)
}

function invalidEmail(email){
    if (email == undefined)
        $("#formAlert").text("L'email inserita non è valida! Riprova.");
    else
        $("#formAlert").html("Esiste gia un account associato a questa email!");
    $("#formAlert").show("");
}

function invalidUsername(username){
    if(username === undefined)
        $("#formAlert").text("Il nome utente inserito non valido! Riprova.");
    else
        $("#formAlert").text(`Il nome utente \"${username}\" non è disponibile!`);

    $("#formAlert").show("");
}

function invalidPass(){
    $("#formAlert").text("La password inserita non è valida.");
    $("#formAlert").show("");
}

function signUp(){
    $("#formAlert").hide()
    
    Swal.fire(
        'Registrazione Eseguita!',
        'Ti rindirizeremo alla tua pagina privata',
        'success'
    ).then((result) => {
        if (result.isConfirmed) {
            window.location.replace("../home_page/index.html");
        }
      })
}

function signUpFail(errore){
    console.log(errore)
    $("#formAlert").html("Si è verificato un errore! <br/> Riprova.");
    $("#formAlert").show("");
}
