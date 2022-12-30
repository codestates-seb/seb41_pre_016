import create from "zustand";
import axios from "axios";
import { Cookies } from "react-cookie";

<<<<<<< HEAD:front/stack-overflow/src/store/zustandUser.js

export const userStore = create((set) => ({
=======
export const signupStore = create((set) => ({
>>>>>>> 1875eef (fit: Fix Navigator bug):front/stack-overflow/src/store/zustandSignup.js
  name: "",
  email: "",
  password: "",
  signupError: null,
  setName: (data) => set({ name: data }),
  setEmail: (data) => set({ email: data }),
  setPassword: (data) => set({ password: data }),
  postUser: async (url, dataObj) => {
    await axios
      .post(url, dataObj)
      .then((response) => {
        set({ signupError: false });
        alert("성공!");
      })
      .catch((err) => {
        set({ signupError: true });
        alert("실패!");
      });
  },
}));
