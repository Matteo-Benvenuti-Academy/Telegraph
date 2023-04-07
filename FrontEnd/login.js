const epFindAll = 'http://172.16.237.206:8080/chat/login';

fetch(epFindAll,
    {
        method: "POST",
        body: JSON.stringify({
            username: "Matteo",
            pass: "1234"
    })
    })
    .then(result => result.json())
    .then(response => { console.log(response); console.log(document.cookie) })
    .catch(errore => console.log(errore));