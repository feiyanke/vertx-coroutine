import io.vertx.core.buffer.Buffer
import io.vertx.core.file.*
import io.vertx.kotlin.coroutines.awaitResult

suspend fun FileSystem.copy(from:String, to:String, option:CopyOptions = CopyOptions())
        = awaitResult<Void> { copy(from, to, it) }
suspend fun FileSystem.copyRecursive(from: String, to: String, recursive:Boolean=true)
        = awaitResult<Void> { copyRecursive(from, to, recursive, it) }
suspend fun FileSystem.move(from:String, to:String, option:CopyOptions = CopyOptions())
        = awaitResult<Void> { move(from, to, it) }
suspend fun FileSystem.truncate(path:String, len:Long)
        = awaitResult<Void> { truncate(path, len, it) }
suspend fun FileSystem.chmod(path:String, perm:String)
        = awaitResult<Void> { chmod(path, perm, it) }
suspend fun FileSystem.chmodRecursive(path:String, perm:String, directoryPerm:String)
        = awaitResult<Void> { chmodRecursive(path, perm, directoryPerm, it) }
suspend fun FileSystem.chown(path:String, user:String?=null, group:String?=null)
        = awaitResult<Void> { chown(path, user, group, it) }
suspend fun FileSystem.props(path:String) : FileProps
        = awaitResult<FileProps> { props(path, it) }
suspend fun FileSystem.lprops(path:String) : FileProps
        = awaitResult<FileProps> { lprops(path, it) }
suspend fun FileSystem.link(lin:String, path: String)
        = awaitResult<Void> { link(lin, path, it) }
suspend fun FileSystem.symlink(lin:String, path: String)
        = awaitResult<Void> { symlink(lin, path, it) }
suspend fun FileSystem.unlink(path:String)
        = awaitResult<Void> { unlink(path, it) }
suspend fun FileSystem.readSymlink(path: String) : String
        = awaitResult { readSymlink(path, it) }
suspend fun FileSystem.delete(path: String)
        = awaitResult<Void> { delete(path, it) }
suspend fun FileSystem.deleteRecursive(path: String, recursive: Boolean=true)
        = awaitResult<Void> { deleteRecursive(path, recursive, it) }
suspend fun FileSystem.mkdir(path: String)
        = awaitResult<Void> { mkdir(path, it) }
suspend fun FileSystem.mkdir(path: String, perm: String)
        = awaitResult<Void> { mkdir(path, perm, it) }
suspend fun FileSystem.mkdirs(path: String)
        = awaitResult<Void> { mkdirs(path, it) }
suspend fun FileSystem.mkdirs(path: String, perm: String)
        = awaitResult<Void> { mkdirs(path, perm, it) }
suspend fun FileSystem.readDir(path: String):List<String>
        = awaitResult { readDir(path, it) }
suspend fun FileSystem.readDir(path: String, filter:String):List<String>
        = awaitResult { readDir(path, filter, it) }
suspend fun FileSystem.readFile(path: String): Buffer
        = awaitResult { readFile(path, it) }
suspend fun FileSystem.writeFile(path: String, buffer: Buffer)
        = awaitResult<Void> { writeFile(path, buffer, it) }
suspend fun FileSystem.open(path: String, option:OpenOptions = OpenOptions()):AsyncFile
        = awaitResult { open(path, option, it) }
suspend fun FileSystem.createFile(path: String)
        = awaitResult<Void> { createFile(path, it) }
suspend fun FileSystem.createFile(path: String, perm: String)
        = awaitResult<Void> { createFile(path, perm, it) }
suspend fun FileSystem.exists(path: String):Boolean
        = awaitResult<Boolean> { exists(path, it) }
suspend fun FileSystem.fsProps(path: String):FileSystemProps
        = awaitResult<FileSystemProps> { fsProps(path, it) }