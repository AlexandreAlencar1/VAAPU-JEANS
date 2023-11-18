//menu

let menu = document.querySelector('.menu-icon');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    navbar.classList.toggle('active');
    menu.classList.toggle('move');
    carrinho.classList.remove('active');
    newItem.classList.remove('active');
    login.classList.remove('active');
    criar.classList.remove('active');
}
 // carrinho verificar 

 let carrinho = document.querySelector('.cart');

 document.querySelector('#cart-icon').onclick =() => {
    carrinho.classList.toggle('active');
    navbar.classList.remove('active');
    newItem.classList.remove('active');
    
   criar.classList.remove('active');
    menu.classList.remove('move');
    login.classList.remove('active');
}

// fazer login
let login = document.querySelector('.login-form');

document.querySelector('#user-icon').onclick =() => {
   login.classList.toggle('active');
    carrinho.classList.remove('active');
   navbar.classList.remove('active');
   menu.classList.remove('move');
   criar.classList.remove('active');
   newItem.classList.remove('active');
}
// criar conta
let criar = document.querySelector('.criar-form');

document.querySelector('#more-user').onclick =() => {
   criar.classList.toggle('active');
    carrinho.classList.remove('active');
   navbar.classList.remove('active');
   menu.classList.remove('move');   
   login.classList.remove('active');
   newItem.classList.remove('active');

}
// newItem conta
let newItem = document.querySelector('.newitem-form');

document.querySelector('#more-item').onclick =() => {
   newItem.classList.toggle('active');
    carrinho.classList.remove('active');
   navbar.classList.remove('active');
   menu.classList.remove('move');   
   login.classList.remove('active');
   criar.classList.remove('active');

}