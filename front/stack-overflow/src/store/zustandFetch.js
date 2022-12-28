import create from "zustand";
import axios from "axios";

export const fetchStore = create((set) => ({
  data: null,
  isLoading: false,
  error: null,
  fetch: async (url) => {
    set({ isLoading: true });
    try{
    const response = await axios.get(url);
    set({ data: await response });
    }catch (e){
      console.log(e);
      set({ error: e.message });
    }
    set({ isLoading: false });
  },
}));
