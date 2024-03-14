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
                sunset: {
                    ...require("daisyui/src/theming/themes")["sunset"],
                    accent: "#FFFDD0"
                },
            },
        ],
    },
}

