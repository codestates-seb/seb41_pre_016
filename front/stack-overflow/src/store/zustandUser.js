import create from "zustand";
import axios from "axios";

export const userStore = create((set) => ({
  name: "",
  email: "",
  password: "",
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
        console.log(response);
      })
      .catch((err) => {
        console.log(err.message);
      });
  },
}));
