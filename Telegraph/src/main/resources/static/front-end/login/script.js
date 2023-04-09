const urlLogin = 'http://localhost:8080/Telegraph/login';


function checkLogin(){
    
    let username = $("#usernameInput").val()
    let pass = $("#passwordInput").val()

    if(username === "" || pass === "")
        return

    fetch(urlLogin,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                pass
            })
        })
        .then(result => result.json())
        .then(response => {
            if(response.status === "ok"){
                login();
            }else{
                loginFail();
            }
        })
        .catch(errore => loginFail(errore));
}

function login(){
    $("#formAlert").hide()
    window.location.replace("../home_page/index.html");
}

function loginFail(errore) {
    $("#passwordInput").val("");
    
    if(errore === undefined){
        $("#formAlert").html("Nome utente o password errate! <br/> Per favore riprova.")
        
    }else{
        console.log(errore)
        $("#formAlert").html("Si è verificato un errore! <br/> Riprova.")
    }

    $("#formAlert").show("");
}