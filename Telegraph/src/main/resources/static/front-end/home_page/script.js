const urlCheckSession = 'http://localhost:8080/Telegraph/session';
const urlChangeAvatar = 'http://localhost:8080/Telegraph/user/change-pic';


async function checkSession(){
    return fetch(urlCheckSession,
        {
            method: "GET",
        })
        .then(result => result.json())
        .then(response => {
            if(response.status === "ok")
                return response.data;
            else
                invalidSession();
        })
        .catch(errore => invalidSession())
}

function invalidSession(){
    window.location.replace("../login/index.html");
}

function loadAvatarPic(userData){
    $("#avatar").html(`<identicon-svg username="${userData.imgcode}" class="profilePic profilePic-preview d-inline-block align-top">`)
}

async function showProfileAllert(){
    let userData = await checkSession()
    
    let username = userData.username;
    let email = userData.email;
    let imgcode = userData.imgcode;
    
    Swal.fire({
        color: 'white',
        background : '#212529',
        width: "50%",
        heightAuto: false,
        customClass: {
            popup:'swal-height',
            actions:'actions',
        },
        showCancelButton: true,
        confirmButtonText: 'Salva',
        cancelButtonText:'Cancella',
        reverseButtons: true,
        html:`
            <div class="container">
                <div class="row">
                    <div class="col-sm spaced-column">
                        <div class="title text" >
                            <span> Profile </span>
                        </div>

                        <div class="text">
                            <span class="information-title">Nome utente</span> <br/>
                            <span class="information-data form-control form-control-lg bg-dark text-white">${username}</span>
                        </div>
                    
                        <div class="text">
                            <span class="information-title">Email</span> <br/>
                            <span class="information-data form-control form-control-lg bg-dark text-white">${email}</span> 
                        </div>

                        <div class="text">
                           
                        </div>

                    </div>
                    <div class="col-sm">
                   
                        <div class="avatar-column">
                            <div class="avatar-div">
                                <div>
                                    <identicon-svg onmouseover="avatarOver()" onmouseleave="avatarNotOver()"  onclick="tempChangeAvatar()" id="svg" username="${imgcode}" class="profilePic avatar d-inline-block align-top"></identicon-svg>
                                    <span onmouseover="avatarOver()" onmouseleave="avatarNotOver()" onclick="tempChangeAvatar()" id="refresh-icon" class = "refresh-icon"> <i class="fa-solid fa-arrows-rotate"></i> </span>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        `
    })
    .then(async (result) => {
        if (result.isConfirmed)
            return changeAvatar()
    });  
}

function avatarOver(){
    $("#svg").css("filter","brightness(50%)");
    $("#refresh-icon").css("display", "block");
}

function avatarNotOver(){
    $("#svg").css("filter","brightness(100%)");
    $("#refresh-icon").css("display", "none");
}

function tempChangeAvatar(){
    let newName = makeid(10)
    $("#svg").attr("username",newName)
}

async function changeAvatar(){
    let imgcode = $("#svg").attr("username")

    await fetch(urlChangeAvatar,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                imgcode
            })
        })
        .then(result => result.json())
        .then(response => {
            console.log(response)
            if(response.status === "ok"){
                loadAvatarPic({imgcode});
            }
            else if(response.status === "invalid session"){
                invalidSession()
            }
        })
        .catch(errore => console.log(errore));
}

$(document).ready(
    async () => {
        
        let userData = await checkSession()
        loadAvatarPic(userData)
    }
)