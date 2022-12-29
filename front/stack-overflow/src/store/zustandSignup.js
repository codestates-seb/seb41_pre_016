import create from "zustand";
import axios from "axios";

export const signupStore = create((set) => ({
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
