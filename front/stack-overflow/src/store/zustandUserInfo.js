import create from "zustand";
import axios from "axios";

export const userInfoStore = create((set) => ({
  userInfo: null,
  isLoading: true,
  error: null,
  getToken: async (url, cookieObj) => {
    set({ isLoading: true });
    try {
      const response = await axios.get(url, { headers: cookieObj });
      set({ data: await response });
      console.log(response);
    } catch (e) {
      console.log(e);
      set({ error: e.message });
    }
    set({ isLoading: false });
  },
}));
