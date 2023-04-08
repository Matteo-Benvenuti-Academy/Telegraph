function checkLogin(){
    const epFindAll = 'http://localhost:8080/chat/login';
    
    const username = $("#usernameInput").val()
    const pass = $("#passwordInput").val()

    if(username === "" || pass === "")
        return


    fetch(epFindAll,
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
    $("#formAlert").show("")
    window.location.replace("../home_page/index.html");
}

function loginFail(errore) {
    $("#passwordInput").val("");
    
    if(errore === undefined){
        $("#formAlert").html("Username o password errate! <br/> Per favore riprova.")
    }else{
        console.log(errore)
        $("#formAlert").html("Si è verificato un errore! <br/> Riprova.")
    }

    $("#formAlert").show("");
}