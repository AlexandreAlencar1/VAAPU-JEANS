//menu

let menu = document.querySelector('.menu-icon');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    navbar.classList.toggle('active');
    menu.classList.toggle('move');
    carrinho.classList.remove('active');
    login.classList.remove('active');
}
 // carrinho verificar 

 let carrinho = document.querySelector('.cart');

 document.querySelector('#cart-icon').onclick =() => {
    carrinho.classList.toggle('active');
    navbar.classList.remove('active');
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
}
