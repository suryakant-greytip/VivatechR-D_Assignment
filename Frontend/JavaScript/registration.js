const baseApi=`http://localhost:8088/vivatech`;

function registerUser(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var mobile = document.getElementById("number").value;

    fetch(`${baseApi}/register`,{
        method:'POST',
        headers:{
            'Content-Type':'application/json',
        },
        body:JSON.stringify({
            'email': email,
            'password': password,
            'mobile': mobile,
        }),
    })
    .then(response=>response.json())
    .then(data=>{
        alert(data);
    })
    .catch(error=>{
        console.error('Error:', error);
    })

}