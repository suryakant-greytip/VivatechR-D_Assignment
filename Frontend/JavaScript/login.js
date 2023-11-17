const baseApi=`http://localhost:8088/vivatech`;

function loginUser() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    // Make AJAX request to /api/login
    fetch(`${baseApi}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            'email': email,
            'password': password,
        }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.token) {
            // Successful login, store the token in sessionStorage or localStorage
            sessionStorage.setItem('token', data.token);
            
            generateOtp(email);
            window.location.href("otpValidate.html");
            validateOtp(email);
            alert('Login successful'); // Display success message
            
        } else {
            alert('Login failed: ' + data.error); // Display error message
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function generateOtp(email) {

    // Make AJAX request to /api/otp/generate
    fetch(`${baseApi}/generateOtp?email=${email}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Bearer ' + sessionStorage.getItem('token'), // Include JWT token in the request header
        },
    })
    .then(response => {
        if(response.status>=200 && response.status<300){
            return response.json();
        }
        else{
            throw new Error("failed to generate Otp!");
        }
    })
    .then(data => {
         alert(data); 
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function validateOtp(email) {
    var otp = document.getElementById("otp").value;

    // Make AJAX request to /api/otp/validate
    fetch(`${baseApi}/validateOtp?email=${email}&otp=${otp}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Bearer ' + sessionStorage.getItem('token'), // Include JWT token in the request header
        },
    })
    .then(response => {
        if(response.status>=200 && response.status<300){
            return response.json();
        }
        else{
            throw new Error("failed to generate Otp!");
        }
    })
    .then(data => {
        if(data.message){
            alert("User Logged In Successfully!");
        }
        else{
            alert("Invalid Otp");
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
