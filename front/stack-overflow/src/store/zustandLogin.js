import create from "zustand";
import { persist } from "zustand/middleware";
import axios from "axios";
import { Cookies } from "react-cookie";

export const loginStore = create((set) => ({
  email: "",
  password: "",
  setEmail: (data) => set({ email: data }),
  setPassword: (data) => set({ password: data }),
  loginError: null,
  isLogin: false,
  setLogin: (data) => set({ isLogin: data }),
  loginPost: async (url, data) => {
    await axios
      .post(url, data)
      .then((response) => {
        const cookies = new Cookies();
        const date = new Date();
        date.setTime(date.getTime() + 30 * 60 * 1000);
        cookies.set("access_jwt", {
          Authorization: response.headers.authorization,
          Refresh: response.headers.refresh,
        });
        set({ loginError: false });
        alert("성공!");
      })
      .catch((err) => {
        set({ loginError: true });
        console.log(err.response.status);
        alert("실패!");
      });
  },
}));
