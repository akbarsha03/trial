export interface IBook {
  id?: number;
  name?: string | null;
  price?: number | null;
  tag?: string;
}

export const defaultValue: Readonly<IBook> = {};
