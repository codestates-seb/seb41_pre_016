import create from "zustand";
import axios from "axios";

export const fetchStore = create((set) => ({
  item: {},
  fetch: async (url) => {
    const response = await axios.get(url);
    set({ item: await response });
  },
}));
export const postStore = create(() => ({
  post: async (url, dataObj) => {
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
