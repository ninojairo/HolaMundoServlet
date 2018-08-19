/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validarForma(formulario){
    var user = formulario.usuario;
    
    if(user.value == "" || user.value == "Escribir Usuario"){
        alert("Digite el usuario");
        user.focus();
        user.select();
        return false;
    }
    
    
    
    //Validacion de password
    var password = formulario.password;
    if(password.value == "" || password.value == "Escribir Password" || password.value.length < 3){
        alert("Digite el password");
        user.focus();
        user.select();
        return false;
    }
    
    //Validacion de Genero
    var genero = formulario.genero;
    var generoSeleccionado = false;
    
    for(i=0;i<genero.length;i++){
        if(genero[i].checked){
            generoSeleccionado = true;
            
        }
    }
    if(!generoSeleccionado){
        alert("Seleccine genero");
        user.focus();
        user.select();
        return false;
    }
    
    //Validacion de tecnologias
    var tecnologias = formulario.tecnologia;
    var checkSeleccionado = false;
    for(var i =0;i<tecnologias.length;i++){
        if(tecnologias[i].checked){
            checkSeleccionado = true;
        }
    }
    if(!checkSeleccionado){
        alert("Seleccine tecnologia");
        user.focus();
        user.select();
        return false;
    }
    
    
    
    
    
}

