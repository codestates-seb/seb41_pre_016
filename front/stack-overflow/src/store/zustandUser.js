import create from "zustand";

export const userStore = create((set) => ({
  name: "",
  email: "",
  password: "",
  setName: (data) => set({ name: data }),
  setEmail: (data) => set({ email: data }),
  setPassword: (data) => set({ password: data }),
}));
