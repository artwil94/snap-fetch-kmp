import SwiftUI

@main
struct iOSApp: App {
   init() {
        ModuleComponentKt.doInitKoin()
        #if DEBUG
            SharedApp.companion.doInitKMMDebugLogs()
        #endif
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}