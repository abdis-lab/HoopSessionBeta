/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
      "src/main/resources/templates/*.html"
  ],
  theme: {
    extend: {},
  },
  plugins: [
      require("@tailwindcss/typography"), require("daisyui")
  ],
    daisyui: {
      themes: [
          {
              mytheme: {
                  "primary": "#00b4d8",
                  "secondary": "#0077b6",
                  "neutral": "#0b1f66",
                  "accent": "#90e0ef",
                  "grey": "#6c757d",
                  "base-100": "#3d4451"
              }
          }
      ]
    }
}

