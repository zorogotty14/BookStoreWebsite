import { defineStore } from 'pinia'
import type { BookItem } from '@/types'
import { ShoppingCart } from '@/models/ShoppingCart'

const CART_STORAGE_KEY = 'ShoppingCart'

export const useCartStore = defineStore('CartStore', {
    state: () => {
        const initCart = new ShoppingCart()
        const cartString = localStorage.getItem(CART_STORAGE_KEY)
        if (cartString !== null) {
            // cartString is a string
            const cartFromStorage = JSON.parse(cartString) as ShoppingCart
            Object.assign(initCart, cartFromStorage)
        }
        return {
            cart: initCart
        }
    },
    getters: {
        count(): number {
            console.log(this.cart.numberOfItems)
            return this.cart.numberOfItems
        },
        calsum(): number{
            let total = 0;
            this.cart.items.forEach((item, i) => {
                total += (item.book.price / 100) * item.quantity;
            });
            return total;
        }

    },

    actions: {
        clearCart() {
            this.cart.clear()
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.cart))
        },
        addToCart(book: BookItem) {
            this.cart.addBook(book)
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.cart))
        },
        updateBookQuantity(book: BookItem, quantity: number) {
            this.cart.update(book, quantity)
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.cart))
        }
    }
})
