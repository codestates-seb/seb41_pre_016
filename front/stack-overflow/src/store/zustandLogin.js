import create from "zustand";
import { persist } from "zustand/middleware";

export const loginStore = create(persist((set) => ({
    isLogin: false,
    setLogin: (data) => set({ isLogin: data }),
})));