import create from "zustand";
import axios from "axios";

export const userInfoStore = create((set) => ({
  userInfo: null,
  isLoading: true,
  error: null,
  setIsLoading: (data) => set({ isLoading: data }),
  setError: (data) => set({ error: data }),
  getToken: async (url, cookieObj) => {
    set({ isLoading: true });
    try {
      const response = await axios.get(url, { headers: cookieObj });
      set({ userInfo: await response.data });
    } catch (e) {
      console.log(e);
      set({ error: e.message });
    }
    set({ isLoading: false });
  },
}));
