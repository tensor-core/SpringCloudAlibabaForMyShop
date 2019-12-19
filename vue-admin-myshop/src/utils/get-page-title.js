import defaultSettings from '@/settings'

const title = defaultSettings.title || 'test'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
