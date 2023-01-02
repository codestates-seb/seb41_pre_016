import create from "zustand";
import axios from "axios";
import { Cookies } from "react-cookie";

export const signupStore = create((set) => ({
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
        window.location.href = "/login";
      })
      .catch((err) => {
        set({ signupError: true });
        alert("실패!");
      });
  },
}));
