import create from "zustand";
import axios from "axios";

<<<<<<< HEAD:front/stack-overflow/src/store/zustandUser.js

export const userStore = create((set) => ({
=======
export const signupStore = create((set) => ({
>>>>>>> 1875eef (fit: Fix Navigator bug):front/stack-overflow/src/store/zustandSignup.js
  name: "",
  email: "",
  password: "",
  signupError:null,
  setName: (data) => set({ name: data }),
  setEmail: (data) => set({ email: data }),
  setPassword: (data) => set({ password: data }),
  postUser: async (url, dataObj) => {
    await axios
      .post(url, {
        name: dataObj.name,
        email: dataObj.email,
        password: dataObj.password,
      })
      .then((response) => {
        set({ signupError: false });
        console.log(response);
      })
      .catch((err) => {
        set({ signupError: true });
        console.log(err.response.status)
      });
  },
}));
