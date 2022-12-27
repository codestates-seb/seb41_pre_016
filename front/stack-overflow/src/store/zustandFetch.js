import create from "zustand";
import axios from "axios";

export const fetchStore = create((set) => ({
  item: {},
  fetch: async (url) => {
    const response = await axios.get(url);
    set({ item: await response });
  },
}));
